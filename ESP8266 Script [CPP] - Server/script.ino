#include <ESP8266WiFi.h>
#include <Ticker.h>
#include <cstring>
#include <iostream>

#define led D8
#define photoRelay D7
#define acp A0
#define motorEnable D2
#define changeMotor D1
#define hollUp D3
#define hollDown D4

using namespace std;
WiFiServer server(80);

boolean stateLed = false;
boolean stateMotorActivity = false;
boolean stateUpHoll = false;
boolean stateDownHoll = false;
boolean reverseMotorSide = false;

boolean stateMotorUp = false;
boolean stateMotorDown = false;
boolean autoMode = false;

int dataInternalSensor[3];
int internalInd = 0;

int dataExternalSensor[3];
int externalInd = 0;

int middleInternalValue = 0;
int middleExternalValue = 0;

Ticker internalData;
Ticker externalData;
Ticker enableMotor;
Ticker disabledMotor;
Ticker changeMotorSide;
Ticker changeReverseMotorSide;
Ticker hollUpData;
Ticker hollDownData;

void setup() {
  
  //Enable mode
  pinMode(led, OUTPUT);
  pinMode(photoRelay, OUTPUT);
  pinMode(motorEnable, OUTPUT);
  pinMode(changeMotor, OUTPUT);
  pinMode(hollUp, INPUT);
  pinMode(hollDown, INPUT);

  //Start data
  
  //Connect to WLAN
  WiFi.begin("lolita", "cardigan92");

  //Enable terminal
  Serial.begin(9600);
  
  
  //Output data
  delay(5000);
  Serial.println(WiFi.localIP());
  
  server.begin();
  internalData.attach(10.0, getInternalData);
  externalData.attach(11.0, getExternalData);
  enableMotor.attach(1.0, switchOnMotor);
  disabledMotor.attach(1.0, switchOffMotor);
  hollUpData.attach(1.0, getHollUpState);
  hollDownData.attach(1.0, getHollDownState);
  changeMotorSide.attach(0.5, getMotorSide);
  changeReverseMotorSide.attach(0.5, getReverseMotorSide);
}

void loop() {
  WiFiClient client = server.available();
  if (client){
    Serial.println("Client connected!");
    while(client){
      int indBuff = 0;
      char buff[20];
      while(client.available()>0){
        char c = client.read();
        
        if (c == 'l'){
          if (stateLed == false){
            digitalWrite(led, HIGH);
            stateLed = true;
            char buff[5] = {'L','E','D','O','N'};
            client.write(buff, sizeof(buff));
          }
          else{
            digitalWrite(led, LOW);
            stateLed = false;
            char buff[6] = {'L','E','D','O','F', 'F'};
            client.write(buff, sizeof(buff));
          }
        }

        if (c == 'u'){
          digitalWrite(changeMotor, LOW);
          if (!stateMotorUp){
              digitalWrite(motorEnable, HIGH);
              stateMotorUp = true;
              char buff[9] = {'M','O','T','O', 'R', 'U', 'P', 'O', 'N'};
              client.write(buff, sizeof(buff));
            }
            else{
              digitalWrite(motorEnable, LOW);
              stateMotorUp = false;
              char buff[10] = {'M','O','T','O', 'R', 'U', 'P', 'O', 'F', 'F'};
              client.write(buff, sizeof(buff));
            }
        }

        if (c == 'd'){
          digitalWrite(changeMotor, HIGH);
          if (!stateMotorDown){
              digitalWrite(motorEnable, HIGH);
              stateMotorDown = true;
              char buff[11] = {'M','O','T','O', 'R', 'D', 'O', 'W', 'N', 'O', 'N'};
              client.write(buff, sizeof(buff));
            }
            else{
              digitalWrite(motorEnable, LOW);
              stateMotorDown = false;
              char buff[12] = {'M','O','T','O', 'R', 'D', 'O', 'W', 'N', 'O', 'F', 'F'};
              client.write(buff, sizeof(buff));
            }
        }

        if (c == 'a'){
          if (autoMode == false){
            autoMode = true;
            char buff[10] = {'A','U','T','O','M','O','D','E','O','N'};
            client.write(buff, sizeof(buff));
          }
          else{
            autoMode = false;
            char buff[11] = {'A','U','T','O','M','O','D','E','O','F','F'};
            client.write(buff, sizeof(buff));
          }
        }

        if (c == '1'){
          char buff[1] = {'0'};
          client.write(buff, sizeof(buff));
        }
      }
    }
  }
}
void getInternalData(){
  if (autoMode){
    digitalWrite(photoRelay, HIGH);
    dataInternalSensor[internalInd] = analogRead(acp);
    Serial.println(dataInternalSensor[internalInd]);
    internalInd++;

    if (internalInd == 3){
      for (int i = 0; i < 3; i++){
        middleInternalValue += dataInternalSensor[i];
      }
      middleInternalValue /= 3;
      internalInd = 0;
    }
   }
}
void getExternalData(){
  if (autoMode){
    digitalWrite(photoRelay, LOW);
    dataExternalSensor[externalInd] = analogRead(acp);
    Serial.println(dataExternalSensor[externalInd]);
    externalInd++;

    if (externalInd == 3){
      for (int i = 0; i < 3; i++){
        middleExternalValue += dataExternalSensor[i];
      }
      middleExternalValue /= 3;
      externalInd = 0;
      getCommonSensorData();
    }
  }
}
void getCommonSensorData(){
    if (middleInternalValue < 450 && middleExternalValue >= 650){
      Serial.println(middleInternalValue);
      Serial.println(middleExternalValue);
      reverseMotorSide = false;
      stateMotorActivity = true;
    }
    else if (middleInternalValue < 450 && middleExternalValue <= 450){
      Serial.println(middleInternalValue);
      Serial.println(middleExternalValue);
      reverseMotorSide = true;
      stateMotorActivity = true;
    }
    middleInternalValue = 0;
    middleExternalValue = 0;
}

void switchOnMotor(){
  if (autoMode){
    if (stateMotorActivity){
      if (stateUpHoll || stateDownHoll){
            stateMotorActivity = false;
      }
      else{
        digitalWrite(motorEnable, HIGH);
      }
    }
  }
}

void switchOffMotor(){
  if (autoMode){
     if (!stateMotorActivity){
        digitalWrite(motorEnable, LOW);
     }
  }
}

void getHollUpState(){
  if (autoMode){
      if (digitalRead(hollUp) == LOW){
        stateUpHoll = true;
      }
      else{
        stateUpHoll = false;
      }
  }
}
void getHollDownState(){
  if (autoMode){
    if (digitalRead(hollDown) == LOW){
      stateDownHoll = true;
    }
    else{
      stateDownHoll = false;
    }
  }
}
void getMotorSide(){
  if (autoMode){
    if (!reverseMotorSide){
      digitalWrite(changeMotor, LOW);
    }
  }
}
void getReverseMotorSide(){
  if (autoMode){
    if (reverseMotorSide){
      digitalWrite(changeMotor, HIGH);
   }
  }
}
