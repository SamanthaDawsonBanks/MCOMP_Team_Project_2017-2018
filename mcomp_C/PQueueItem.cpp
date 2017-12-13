#include "PQueueItem.h"


PQueueItem::PQueueItem(Waypoint w) {
   this->data = w;
}



PQueueItem::~PQueueItem() {
  // TODO Auto-generated destructor stub
}



Waypoint PQueueItem::getData(){
	return Waypoint;
}



PQueueItem PQueueItem::getNext(){
	return next;
}



void PQueueItem::setNext(PQueueItem next){
	this->next = next;
}
