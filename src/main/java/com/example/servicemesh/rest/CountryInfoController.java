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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.servicemesh.rest.model.Capitol;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CountryInfoController {
   
    CountryInfoService countryInfoService;
    public CountryInfoController() {
        countryInfoService = new CountryInfoServiceImpl();
    }
    @Path(value = "getCapitol/{name}")
    @GET
    public String getCapitol(@PathParam("name") String name) {
        return countryInfoService.getCapitol(name);
    }  

    @Path(value = "addCapitol")
    @POST
    public String predict(Capitol capitol) {
        return countryInfoService.addCapitol(capitol.getName(), capitol.getCapitol(), capitol.getType());
    }
}