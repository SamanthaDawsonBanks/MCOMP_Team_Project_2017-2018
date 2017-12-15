#ifndef PathItem_H_
#define PathItem_H_
#include "Waypoint.h"




class PathItem {

 private:
  PathItem next;
  Waypoint data;

 public:
  PathItem(Waypoint);
  ~PathItem();



  Waypoint getData();
  PathItem getNext();
  void setNext(PathItem next);
};







#endif /* PathItem_H_ */
