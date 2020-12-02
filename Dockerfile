# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

FROM registry.access.redhat.com/ubi8/ubi-minimal

ENV DATA_DIR=/opt/data

ADD /src/main/resources/data/* $DATA_DIR/

WORKDIR /work/
COPY target/*-runner /work/application
RUN chmod 775 /work
EXPOSE 5500
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]