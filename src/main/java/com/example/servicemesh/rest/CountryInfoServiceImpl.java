// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example.servicemesh.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import com.example.servicemesh.rest.model.Capitol;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CountryInfoServiceImpl implements CountryInfoService {

    public CountryInfoServiceImpl() {
    }
    /**
     * Returns the capitol of the named entity.
     * @param name Named entity, e.g. country
     * @return capitol name or "Unknown"
     */
    @Override
    public String getCapitol(String name)
    {
        Map<String, Capitol> capitolMap = CountryInfoApplication.getCapitols();
        if (capitolMap == null)
        {
            return "Error retrieving data.";
        } 
        Capitol capitol = capitolMap.get(name);
        StringBuilder sb = new StringBuilder("{\"capitol\":\"");
        if (capitol != null) {
            
            sb.append(capitol.getCapitol());
        } else {
            sb.append("Unknown");
        }
        sb.append("\"}");
        return sb.toString();
    }

    @Override
    public String addCapitol(String name, String capitol, String type){

    
        // search to see if the capitol exists
        String cap = getCapitol(capitol);
        // get the capitol ifit exists
        StringBuilder sb = new StringBuilder("{\"result\":\"");

        Map<String, Capitol> capitolMap = CountryInfoApplication.getCapitols();
        if (capitolMap == null) {        
            sb.append("error inserting");
        } else {
            for (int i=0; i< 100; i++) {
                capitolMap.remove(name);
                // remove first
                Capitol capitolInstance = new Capitol();
                capitolInstance.setName(name);
                capitolInstance.setCapitol(capitol);
                capitolInstance.setType(type);
                // then add
                capitolMap.put(name, capitolInstance);
                saveJsonToFile(capitolMap);
          }
            sb.append("Inserted the capitol " + capitol);
        }
       sb.append("\"}");
       return sb.toString();
    }


    private void saveJsonToFile(Map<String, Capitol> capitolMap) {
        try {
            List<Capitol> targetList = new ArrayList<Capitol>(capitolMap.values());
            Jsonb jsonb = JsonbBuilder.create();
            String result = jsonb.toJson(targetList);
            Path path = Paths.get("/opt/data/capitols-quarkus-out.json");
            byte[] strToBytes = result.getBytes();
            Files.write(path, strToBytes);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void saveJsonToFileOrig(Map<String, Capitol> capitolMap) {
        List<Capitol> targetList = new ArrayList<Capitol>(capitolMap.values());
        Gson gson = new Gson();
        try {
        gson.toJson(targetList, new FileWriter("/opt/data/capitols-quarkus-out.json"));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
