/*
 * tests.cpp
 *
 *  Created on: 20 Mar 2018
 *      Author: David Avery 15823926
 */

#include "tests.h"
#include "..\config\robot_config.h"

//--------------------------------------Tests--------------------------------------
void testPath() {
  DEBUG.begin(DEBUGRATE);
  delay(1000);
  DEBUG.println("Begin testPath");

  Waypoint q;

  Waypoint d(100, 100);
  Path p(d);

  q = p.poll();
  DEBUG.print(q.getX());
  DEBUG.print(",");
  DEBUG.print(q.getY());
  DEBUG.println("");

  p.addNode(Waypoint(200, 200));

  q = p.poll();
  DEBUG.print(q.getX());
  DEBUG.print(",");
  DEBUG.print(q.getY());
  DEBUG.println("");

  q = p.poll();
  DEBUG.print(q.getX());
  DEBUG.print(",");
  DEBUG.print(q.getY());
  DEBUG.println("");

  p.addNode(Waypoint(250, 250));
  p.addNode(Waypoint(350, 350));

  q = p.poll();
  DEBUG.print(q.getX());
  DEBUG.print(",");
  DEBUG.print(q.getY());
  DEBUG.println("");

  q = p.poll();
  DEBUG.print(q.getX());
  DEBUG.print(",");
  DEBUG.print(q.getY());
  DEBUG.println("");

  for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
      Waypoint x(i, j);
      p.addNode(x);
    }
  }

  for (int k = 0; k < 10; k++) {
    for (int l = 0; l < 10; l++) {
      q = p.poll();
      DEBUG.print(q.getX());
      DEBUG.print(",");
      DEBUG.print(q.getY());
      DEBUG.println("");
    }
  }

  DEBUG.println("end of data");
  q = p.poll();
  DEBUG.print(q.getX());
  DEBUG.print(",");
  DEBUG.print(q.getY());
  DEBUG.println("");

  q = p.poll();
  DEBUG.print(q.getX());
  DEBUG.print(",");
  DEBUG.print(q.getY());
  DEBUG.println("");

  q = p.poll();
  DEBUG.print(q.getX());
  DEBUG.print(",");
  DEBUG.print(q.getY());
  DEBUG.println("");

  DEBUG.println("End testPath");
}

void testWaypoint() {
  DEBUG.begin(DEBUGRATE);
  delay(1000);
  DEBUG.println("Begin testWaypoint");

  Waypoint t(0, 0);  // = new Waypoint(0,0);
  DEBUG.println("end of data input");
  DEBUG.print(t.getX());
  DEBUG.print(",");
  DEBUG.print(t.getY());
  DEBUG.println("");

  DEBUG.println("End testWaypoint");
}

void testMove() {
  DEBUG.begin(DEBUGRATE);
  delay(1000);
  Waypoint result;
  Propulsion p = Propulsion();
//  DEBUG.println("Begin testMove");
//  DEBUG.println("Calling Drive...");
//  Waypoint target = Waypoint(3276.0, 3276.0); //Near theoretical max
//  result = p.Drive(target);
//  DEBUG.print(result.getX());
//  DEBUG.print(" , ");
//  DEBUG.print(result.getY());
//  DEBUG.print(" ");
//  DEBUG.println(p.getHeading());
//  DEBUG.println("Test 1 complete");

  delay(5000);

  DEBUG.println("Calling Drive...");
  Waypoint target2 = Waypoint(-50.0, -50.0);
  result = p.Drive(target2);
  DEBUG.print(result.getX());
  DEBUG.print(" , ");
  DEBUG.print(result.getY());
  DEBUG.print(" ");
  DEBUG.println(p.getHeading());
  DEBUG.println("Test 2 complete");

  delay(5000);

  DEBUG.println("Calling Drive...");
  Waypoint target3 = Waypoint(5.0, 0.0);
  result = p.Drive(target3);
  DEBUG.print(result.getX());
  DEBUG.print(" , ");
  DEBUG.print(result.getY());
  DEBUG.print(" ");
  DEBUG.println(p.getHeading());
  DEBUG.println("Test 3 complete");

  DEBUG.println("Calling Drive...");
  Waypoint target4 = Waypoint(-250.0, 0.0);
  DEBUG.print(target4.toAngleDistance().getTheta());
  DEBUG.print(" , ");
  DEBUG.print(target4.toAngleDistance().getDistance());
  DEBUG.print(" ");
  result = p.Drive(target4);
  DEBUG.print(result.getX());
  DEBUG.print(" , ");
  DEBUG.print(result.getY());
  DEBUG.print(" ");
  DEBUG.println(p.getHeading());
  DEBUG.println("Test 4 complete");

}

