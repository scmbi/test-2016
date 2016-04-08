package it.netgrid.rbtrees;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import it.netgrid.rbtrees.RbTreeElement.Color;

public class RbTreeRightTest {

	RbTree<String> classUnderTest;
	
	@Test
	public void testLeftRotateOne() {
		RbTree<String> classUnderTest =RbTreeGenerator.one();//crea un albero con un nodo
		assertThat("root is black", classUnderTest.getRoot().getColor(), equalTo(Color.BLACK));
		assertThat("null left", classUnderTest.getRoot().getLeft(), equalTo(null));
		assertThat("null right", classUnderTest.getRoot().getRight(), equalTo(null));
	}
	
	@Test
	public void TestLeftRotateSix(){
		RbTree<String> classUnderTest =RbTreeGenerator.six();
		assertThat("root is black", classUnderTest.getRoot().getColor(), equalTo(Color.BLACK));
		assertThat("not null left", classUnderTest.getRoot().getLeft(), notNullValue());
		assertThat("not null right", classUnderTest.getRoot().getRight(), notNullValue());
		assertThat("not equal", classUnderTest.getRoot().getLeft(), not(equals(classUnderTest.getRoot().getRight())));
	}
}