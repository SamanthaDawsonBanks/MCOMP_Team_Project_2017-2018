#ifndef COMMON_MOVEMENT_DRIVE_H_
#define COMMON_MOVEMENT_DRIVE_H_


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
  double dx, dy;

 public:
    Drive(double x, double y);
    virtual~Drive();
};


#endif /* COMMON_MOVEMENT_DRIVE_H_ */
