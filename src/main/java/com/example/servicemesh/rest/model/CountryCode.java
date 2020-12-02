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

package com.example.servicemesh.rest.model;

import java.io.Serializable;
/**
 * Model class to hold country names and codes.
 */
public class CountryCode implements Serializable {

  private static final long serialVersionUID = 1L;

    //This field will be used as a primary key, every POJO should have one
  private String code;
  private String name;

  public String getCode() {
      return code;
  }

  public void setCode(String code) {
      this.code = code;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

}