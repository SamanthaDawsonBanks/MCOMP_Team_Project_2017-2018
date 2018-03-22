/*
 * AngleDistance.cpp
 *
 *  Created on: 22 Mar 2018
 *      Author: David Avery 15823926
 */

#include "AngleDistance.h"

AngleDistance::AngleDistance() {
  // TODO Auto-generated constructor stub
  theta = 0;
  distance = 0;
}

AngleDistance::AngleDistance(double t, long d) {
  // TODO Auto-generated constructor stub
  theta = t;
  distance = d;
}

AngleDistance::~AngleDistance() {
  // TODO Auto-generated destructor stub
}

double AngleDistance::getTheta() {
  return theta;
}

long AngleDistance::getDistance() {
  return distance;
}

