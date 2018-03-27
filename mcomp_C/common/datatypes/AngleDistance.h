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
  AngleDistance(double, long);
  virtual ~AngleDistance();
  double getTheta();
  long getDistance();

};

#endif /* COMMON_DATATYPES_ANGLEDISTANCE_H_ */
