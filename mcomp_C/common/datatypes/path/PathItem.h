#ifndef PathItem_H_
#define PathItem_H_

#include "../Waypoint.h"
#include "PathItem.h"

class PathItem {

 private:
  PathItem* next;
  Waypoint data;

 public:
  PathItem(Waypoint w);
  ~PathItem();

  Waypoint getData();
  PathItem getNext();
  bool setNext(PathItem next);
};

#endif /* PathItem_H_ */
