package com.harshramesh.zuul.narrator;

import static java.lang.Math.random;

public class Narrator {

	private static enum Attribute {
		REPEATABLE, RANDOM, LAST_REPEAT, DEFAULT;

		private static Attribute[] attributes;

		static {
			attributes = Attribute.class.getEnumConstants();
		}

		public static boolean isAttribute(String string) {
			for (Attribute a : attributes) {
				if (a.toString().replaceAll("_", " ").equalsIgnoreCase(string))
					return true;
			}
			return false;
		}

		public static Attribute get(String string) {
			for (int i = 0; i < attributes.length; i++) {
				if (attributes[i].toString().replaceAll("_", " ")
						.equalsIgnoreCase(string))
					return attributes[i];
			}

			return DEFAULT;
		}

		public static String getAllAttributes() {
			StringBuilder builder = new StringBuilder(
					"\n---------------------------------------\n");

			// Adds commands to the string builder
			for (int i = 0; i < attributes.length; i++) {
				builder.append(attributes[i].toString());
				if (i == attributes.length - 2)
					builder.append(", & ");
				else if (i < attributes.length - 2)
					builder.append(", ");
				if (i != 0 && i % 5 == 0)
					builder.append("\n---------------------------------------\n");
			}
			builder.append("\n---------------------------------------\n");

			// Returns the list of commands
			return builder.toString();
		}

	}

	public static boolean isAttribute(String string) {
		return Attribute.isAttribute(string);
	}

	public static String getAllAttributes() {
		return Attribute.getAllAttributes();
	}

	private Attribute attr;

	private String[] text;

	private int position;

	public Narrator() {
		super();
		position = 0;
	}

	public Narrator(String[] text, String attribute) {
		this();
		this.text = text;
		this.attr = Attribute.get(attribute);
	}

	public String getNextMessage() {
		switch (attr) {
		case REPEATABLE:
			position = position + 1 == text.length ? 0 : position + 1;
			return position == 0 ? text[text.length - 1] : text[position - 1];
		case RANDOM:
			return text[(int) (random() * text.length)];
		case LAST_REPEAT:
			String text1 = this.text[position];
			position = position + 1 == this.text.length ? position
					: position + 1;
			return text1;
		case DEFAULT:
			return position + 1 > text.length - 1 ? null
					: text[++position - 1];
		}
		return null;
	}

}