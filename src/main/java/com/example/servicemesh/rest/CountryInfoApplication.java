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


import java.util.HashMap;
import java.util.Map;
import com.example.servicemesh.rest.model.Capitol;
import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CountryInfoApplication {
	private static Map<String, Capitol> capitolMap = null;

	public static Map<String, Capitol> getCapitols() {
		if (capitolMap == null) {
			init();			
		}
		return capitolMap;
	}

	private static void init(){
		//Actual location on disk for database files, process should have read-write permissions to this folder
		final String filesLocation = "/opt/data/capitols-quarkus-data.json";
		capitolMap = new HashMap<String, Capitol>();
		//Java package name where POJO's are present
		Gson gson = new Gson();

		try {
			// create collection and insert default data
			String capitolsJson = new String ( Files.readAllBytes( Paths.get(filesLocation) ) );
			Capitol capitols[] = gson.fromJson(capitolsJson ,Capitol[].class);

			for (Capitol capitol:capitols) {
				capitolMap.put(capitol.getName(),capitol);
			}
		} catch (java.io.IOException io) {
			io.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		init();
	}

}
