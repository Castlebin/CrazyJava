package ch14.se03.repeatable;

@FkTag(age=5)
@FkTag(name="哈哈哈哈哈", age=9)
public class FkTagsTest {
	public static void main(String[] args) {
		Class<FkTagsTest> clazz = FkTagsTest.class;
		FkTag[] tags = clazz.getDeclaredAnnotationsByType(FkTag.class);
		for(FkTag tag : tags) {
			System.out.println(tag.name() + "-->" + tag.age());
		}
		
		FkTags container = clazz.getDeclaredAnnotation(FkTags.class);
		System.out.println(container);
	}
}
