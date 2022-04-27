#include <ESP8266WiFi.h>
#include <cstring>
#include <iostream>

using namespace std;
WiFiServer server(80);

boolean stateLed = false;
boolean stateMotor = false;

void setup() {
  pinMode(LED_BUILTIN, OUTPUT);
  pinMode(D1, OUTPUT);
  digitalWrite(D1, HIGH);
  WiFi.begin("name", "password");
  Serial.begin(9600);
  delay(5000);
  Serial.println(WiFi.localIP());
  
  server.begin();
}

void loop() {
  WiFiClient client = server.available();
  if (client){
    Serial.println("Hello!");
    while(client){
      char buff[1000];
      int indBuff = 0;
      while(client.available()>0){
        char c = client.read();
        if (c == '1'){
          if (getLedState() == false){
            digitalWrite(D1, LOW);
            stateLed = true;
            char buff[5] = {'L','E','D','O','N'};
            client.write(buff, sizeof(buff));
          }
          else{
            digitalWrite(D1, HIGH);
            stateLed = false;
            char buff[6] = {'L','E','D','O','F', 'F'};
            client.write(buff, sizeof(buff));
          }
        }
        if (c == '0'){
          if (getMotorState() == false){
            digitalWrite(LED_BUILTIN, LOW); 
            stateMotor = true;
            char buff[9] = {'M','O','T','O','R','U','P','O','N'};
            client.write(buff, sizeof(buff));
          }
          else{
            digitalWrite(LED_BUILTIN, HIGH);
            stateMotor = false;
            char buff[10] = {'M','O','T','O','R','U','P','O','F', 'F'};
            client.write(buff, sizeof(buff));
          }
        }

      }
    }
  }
}
bool getLedState(){
  return stateLed;
}
bool getMotorState(){
  return stateMotor; 
}
