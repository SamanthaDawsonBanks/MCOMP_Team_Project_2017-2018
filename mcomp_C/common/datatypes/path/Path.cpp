#include "Path.h"
#include "../Waypoint.h"
#ifndef NULL
#define NULL   ((void *) 0)
#endif

Path::Path(Waypoint w) {
  PathItem head;
  PathItem destination;
  PathItem lastItem;
  int *ptr = NULL;
  this->destination = new PathItem(w);
  this->head = NULL;
  this->lastItem = NULL;
  this->length = 0;

}

Path::~Path() {
  // TODO Auto-generated destructor stub
}


void Path::addNode(Waypoint w){

	if(length > 0){
		PathItem = new PathItem(w);
		lastItem.setNext(PathItem(w));
		lastItem = lastItem.getNext();
	}
	else {
		head = new PathItem(w);
		lastItem = head;
	}

	length++;
}


Waypoint Path::poll(){

	Waypoint res = destination.getData();
	if(length > 0){
		res = head.getData();
		head = head.getNext();

	if(head == NULL){
		lastItem = NULL;
	}
	length--;
	}
	return res;
}



int Path::getLength() {
	int counter = 0;
	PathItem head1 = head;
	if(head == NULL){
		return 0;
	}
	while (head1 != NULL){
		counter++;
		head1 = head1.getNext();
	}
	return counter;
}


bool Path::isDone() {
     return true;
	 return(length == 0);

}



