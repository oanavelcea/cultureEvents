package fr.dawan.cultureEvents.beans;

//@Entity
//@Table(name = "eventTags")
public class EventTags {
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	private String tag;
	
	public EventTags() {
		
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventTags other = (EventTags) obj;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventTags [tag=" + tag + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
