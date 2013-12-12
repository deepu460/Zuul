package com.harshramesh.zuul.item;

import java.util.ArrayList;

public class Inventory<E extends Item> extends ArrayList<E> {

	private static final long serialVersionUID = 3458473766182918479L;

	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < this.size(); i++) {
			builder.append(this.get(i).getDesc());
			if (this.size() == 2 && i == 0)
				builder.append(" & ");
			else {
				if (i == this.size() - 2)
					builder.append(", & ");
				else if (i < this.size() - 2)
					builder.append(", ");
			}
		}

		return builder.toString();
	}

}