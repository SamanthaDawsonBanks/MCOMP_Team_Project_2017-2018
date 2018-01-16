#ifndef PathItem_H_
#define PathItem_H_

#include "../Waypoint.h"
#include "PathItemPtr.h"

class PathItem {

 private:
  PathItemPtr next;
  Waypoint data;

 public:
  PathItem(Waypoint w);
  ~PathItem();

  Waypoint getData();
  PathItemPtr getNext();
  bool setNext(PathItem next);
};







#endif /* PathItem_H_ */
