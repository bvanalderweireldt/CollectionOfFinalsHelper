# CollectionOfFinalsHelper
Helper classes to work with Collections of final classes that don't implement the excepted equals or hashCode method, or even doesn't implement any.

Use it when you are dealing with a Java class that is used in a Collection, and you can't update the class or even extend it as it's a final class !

## Installation
```gradle
gradle build
```

## Usage
```java
private static final class CannotChangeMe {
	String id;

	public CannotChangeMe(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}

final Junction<CannotChangeMe> junctions = new Junction<>(
		(final CannotChangeMe t1, final CannotChangeMe t2) -> t1.getId().equals(t2.getId()), list1,
		list2);

junctions.getDisjonctionList1().size(); // Get unique element of list1
junctions.getDisjonctionList2().size(); // Get unique element of list2
junctions.getConjunction().size();		// Get common element of list1 and list2

```
## Contributing
1. Fork it!
## History
March 30th 2016 - inital version
