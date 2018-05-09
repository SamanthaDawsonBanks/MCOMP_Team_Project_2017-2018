/*
 * AngleDistance.h
 *
 *  Created on: 22 Mar 2018
 *      Author: David Avery 15823926
 */

#ifndef ANGLEDISTANCE_H_
#define ANGLEDISTANCE_H_

class AngleDistance {
 private:
  double theta;
  long distance;

 public:
  AngleDistance();
  AngleDistance(double theta, long distance);
  virtual ~AngleDistance();
  double getTheta();
  long getDistance();

};

#endif /* ANGLEDISTANCE_H_ */
