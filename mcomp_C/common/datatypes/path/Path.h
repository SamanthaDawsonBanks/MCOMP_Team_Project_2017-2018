#include "PathItem.h"
#include "../Waypoint.h"

#ifndef PATH_H_
#define PATH_H_

class Path {

private:
 PathItem head;
 PathItem destination;
 PathItem lastItem;


public:
  Path(Waypoint w);
  virtual ~Path();
  void addNode(Waypoint);
  Waypoint poll();
  int getLength();
  int length = 0;
  bool isDone();


};
//FIXME implement this once data structure is defined in java land

#endif /* PATH_H_ */
