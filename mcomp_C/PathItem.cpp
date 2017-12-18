#include "PathItem.h"


PathItem::PathItem(Waypoint w) {
   this->data = w;
}



PathItem::~PathItem() {
  // TODO Auto-generated destructor stub
}



Waypoint PathItem::getData(){
	return Waypoint;
}



PathItem PathItem::getNext(){
	return next;
}



void PathItem::setNext(PathItem next){
	this->next = next;
}
