/*
 * tests.cpp
 *
 *  Created on: 20 Mar 2018
 *      Author: David Avery 15823926
 */

#include "tests.h"


//--------------------------------------Tests--------------------------------------
void testPath() {
  Serial.begin(9600);
  delay(1000);
  Serial.println("Begin testPath");

  Waypoint q;

  Waypoint d(100, 100);
  Path p(d);

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  p.addNode(Waypoint(200, 200));

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  p.addNode(Waypoint(250, 250));
  p.addNode(Waypoint(350, 350));

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
      Waypoint x(i, j);
      p.addNode(x);
    }
  }

  for (int k = 0; k < 10; k++) {
    for (int l = 0; l < 10; l++) {
      q = p.poll();
      Serial.print(q.getX());
      Serial.print(",");
      Serial.print(q.getY());
      Serial.println("");
    }
  }

  Serial.println("end of data");
  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  Serial.println("End testPath");
}

void testWaypoint() {
  Serial.begin(9600);
  delay(1000);
  Serial.println("Begin testWaypoint");

  Waypoint t(0, 0);  // = new Waypoint(0,0);
  Serial.println("end of data input");
  Serial.print(t.getX());
  Serial.print(",");
  Serial.print(t.getY());
  Serial.println("");

  Serial.println("End testWaypoint");
}

void testMove(){
  Serial.begin(9600);
  delay(1000);
  Serial.println("Begin testMove");
  Serial.println("Calling Drive...");
  Waypoint target = Waypoint(10000000.1, 10000000.0);
  Propulsion p = Propulsion();
  Waypoint result = p.Drive(target);
  Serial.print(result.getX());
  Serial.print(" , ");
  Serial.print(result.getY());
}

