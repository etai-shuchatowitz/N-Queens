
public class Node<E> 
{
	//declare the instance variables
	private E data;
	private Node<E> link;
	
	
	public Node(E initialData, Node<E> initialLink) 
	{
		data = initialData;
		link = initialLink;
	}
	
	public void addNodeAfter(E item) 
	{
		link = new Node<E>(item, link);
	}
	
	public E getData( ) 
	{
		return data;
	}
	
	public Node<E> getLink( )
	{
		return link;
	}

	public void removeNodeAfter( ) 
	{
		link = link.link;
	}
	public void setData(E newData)
	{
		data = newData;
	}
	
	@SuppressWarnings("unchecked")
	public void setLink(@SuppressWarnings("rawtypes") Node newLink) {
		link = newLink;
	}

}
