/*
 * Drive.h
 *
 *  Created on: 22 February 2018
 *      Authors: Stephen Pope 15836791
 *      		 Harry Jackson 14812630
 *         		 David Avery 15823926
 */

#ifndef DRIVE_H_
#define DRIVE_H_

class Drive{

 private:
	double theta;
	double distance;
	double R;
	double phi;
	double wheelRadius;
	double robotRadius;
	double totalTurnAngle;
	double pulse;
	double turnDistance;
	double moveDistance;

 public:
    Drive(double x, double y);
    virtual~Drive();
};

#endif /* DRIVE_H_ */
