package org.odlabs.wiquery.tester.matchers;

import java.lang.reflect.Array;

import org.apache.wicket.Component;

public class ComponentMatchers {
	/**
	 * Copies the specified range of the specified array into a new array. The
	 * initial index of the range (<tt>from</tt>) must lie between zero and
	 * <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt>
	 * is placed into the initial element of the copy (unless
	 * <tt>from == original.length</tt> or <tt>from == to</tt>). Values from
	 * subsequent elements in the original array are placed into subsequent
	 * elements in the copy. The final index of the range (<tt>to</tt>), which
	 * must be greater than or equal to <tt>from</tt>, may be greater than
	 * <tt>original.length</tt>, in which case <tt>null</tt> is placed in all
	 * elements of the copy whose index is greater than or equal to
	 * <tt>original.length - from</tt>. The length of the returned array will be
	 * <tt>to - from</tt>.
	 * <p>
	 * The resulting array is of exactly the same class as the original array.
	 * 
	 * @param original
	 *            the array from which a range is to be copied
	 * @param from
	 *            the initial index of the range to be copied, inclusive
	 * @param to
	 *            the final index of the range to be copied, exclusive. (This
	 *            index may lie outside the array.)
	 * @return a new array containing the specified range from the original
	 *         array, truncated or padded with nulls to obtain the required
	 *         length
	 * @throws ArrayIndexOutOfBoundsException
	 *             if <tt>from &lt; 0</tt> or
	 *             <tt>from &gt; original.length()</tt>
	 * @throws IllegalArgumentException
	 *             if <tt>from &gt; to</tt>
	 * @throws NullPointerException
	 *             if <tt>original</tt> is null GET FROM THE JDK 1.6
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] copyOfRange(T[] original, int from, int to) {
		return copyOfRange(original, from, to, (Class<T[]>) original.getClass());
	}

	/**
	 * Copies the specified range of the specified array into a new array. The
	 * initial index of the range (<tt>from</tt>) must lie between zero and
	 * <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt>
	 * is placed into the initial element of the copy (unless
	 * <tt>from == original.length</tt> or <tt>from == to</tt>). Values from
	 * subsequent elements in the original array are placed into subsequent
	 * elements in the copy. The final index of the range (<tt>to</tt>), which
	 * must be greater than or equal to <tt>from</tt>, may be greater than
	 * <tt>original.length</tt>, in which case <tt>null</tt> is placed in all
	 * elements of the copy whose index is greater than or equal to
	 * <tt>original.length - from</tt>. The length of the returned array will be
	 * <tt>to - from</tt>. The resulting array is of the class <tt>newType</tt>.
	 * 
	 * @param original
	 *            the array from which a range is to be copied
	 * @param from
	 *            the initial index of the range to be copied, inclusive
	 * @param to
	 *            the final index of the range to be copied, exclusive. (This
	 *            index may lie outside the array.)
	 * @param newType
	 *            the class of the copy to be returned
	 * @return a new array containing the specified range from the original
	 *         array, truncated or padded with nulls to obtain the required
	 *         length
	 * @throws ArrayIndexOutOfBoundsException
	 *             if <tt>from &lt; 0</tt> or
	 *             <tt>from &gt; original.length()</tt>
	 * @throws IllegalArgumentException
	 *             if <tt>from &gt; to</tt>
	 * @throws NullPointerException
	 *             if <tt>original</tt> is null
	 * @throws ArrayStoreException
	 *             if an element copied from <tt>original</tt> is not of a
	 *             runtime type that can be stored in an array of class
	 *             <tt>newType</tt>. GET FROM THE JDK 1.6
	 */
	@SuppressWarnings("unchecked")
	public static <T, U> T[] copyOfRange(U[] original, int from, int to,
			Class<? extends T[]> newType) {
		int newLength = to - from;
		if (newLength < 0)
			throw new IllegalArgumentException(from + " > " + to);
		T[] copy = ((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength]
				: (T[]) Array
						.newInstance(newType.getComponentType(), newLength);
		System.arraycopy(original, from, copy, 0,
				Math.min(original.length - from, newLength));
		return copy;
	}

	public static AndMatcher and(ComponentMatcher left, ComponentMatcher right) {
		return new AndMatcher(left, right);
	}

	public static AndMatcher and(ComponentMatcher first,
			ComponentMatcher second, ComponentMatcher... others) {
		if (others == null || others.length == 0) {
			return and(first, second);
		}
		ComponentMatcher firstOfOthers = others[0];
		ComponentMatcher[] remainderOfOthers = copyOfRange(others, 1,
				others.length - 1, ComponentMatcher[].class);

		return and(first, and(second, firstOfOthers, remainderOfOthers));
	}

	public static OrMatcher or(ComponentMatcher left, ComponentMatcher right) {
		return new OrMatcher(left, right);
	}

	public static ComponentIdMatcher withId(String id) {
		return new ComponentIdMatcher(id);
	}

	public static PropertyExpressionMatcher forProperty(String expression) {
		return new PropertyExpressionMatcher(expression);
	}

	public static ComponentTypeMatcher ofType(Class<? extends Component> type) {
		return new ComponentTypeMatcher(type);
	}

	public static ModelMatcher forModel(Object modelObject) {
		return new ModelMatcher(modelObject);
	}

	public static ParentMatches parentMatches(ComponentMatcher matcher) {
		return new ParentMatches(matcher);
	}

	public static ChildMatches childMatches(ComponentMatcher matcher) {
		return new ChildMatches(matcher);
	}
}
