#include "PathItem.h"


PathItem::PathItem(Waypoint w) {
   this->data = w;
   this->next = this;
}

PathItem::~PathItem() {
  // TODO Auto-generated destructor stub
}

Waypoint PathItem::getData(){
	return data;
}

PathItem PathItem::getNext(){
	return *next;
}

bool PathItem::setNext(PathItem next){
	this->next = &next;
	return true;
}
