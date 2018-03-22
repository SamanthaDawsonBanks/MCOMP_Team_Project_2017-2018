/*
 * AngleDistance.h
 *
 *  Created on: 22 Mar 2018
 *      Author: David Avery 15823926
 */

#ifndef COMMON_DATATYPES_ANGLEDISTANCE_H_
#define COMMON_DATATYPES_ANGLEDISTANCE_H_

class AngleDistance {
 private:
  double theta;
  long distance;

 public:
  AngleDistance();
  AngleDistance(double theta, long distance);
  virtual ~AngleDistance();
  int getTheta();
  int getDistance();

};

#endif /* COMMON_DATATYPES_ANGLEDISTANCE_H_ */
