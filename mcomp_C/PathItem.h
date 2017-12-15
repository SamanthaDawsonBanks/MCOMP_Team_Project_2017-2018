#ifndef PQUEUEITEM_H_
#define PQUEUEITEM_H_
#include "Waypoint.h"




class PQueueItem {

 private:
  PQueueItem next;
  Waypoint data;

 public:
  PQueueItem(Waypoint);
  ~PQueueItem();



  Waypoint getData();
  PQueueItem getNext();
  void setNext(PQueueItem next);
};







#endif /* PQUEUEITEM_H_ */
