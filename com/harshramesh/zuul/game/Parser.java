package com.harshramesh.zuul.game;

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import com.harshramesh.zuul.command.Command;
import com.harshramesh.zuul.command.CommandWord;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and tries
 * to interpret the line as a two word command. It returns the command as an
 * object of class Command.
 * 
 * The parser has a set of known command words. It checks user input against the
 * known commands, and if the input is not one of the known commands, it returns
 * a command object that is marked as an unknown command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser {

	private Scanner reader; // source of command input

	/**
	 * Create a parser to read from the terminal window.
	 */
	public Parser() {
		reader = new Scanner(in);
	}

	/**
	 * @return The next command from the user.
	 */
	public Command getCommand() {
		String inputLine; // will hold the full input line
		String word1 = null;
		String word2 = null;

		out.print("\n> "); // print prompt

		inputLine = reader.nextLine();

		// Find up to two words on the line.
		StringTokenizer tokenizer = new StringTokenizer(inputLine, " ");
		if (tokenizer.hasMoreTokens()) {
			word1 = tokenizer.nextToken(); // get first word
			if (tokenizer.hasMoreTokens())
				word2 = tokenizer.nextToken(); // get second word
			// note: we just ignore the rest of the input line.
		}

		// Now check whether this word is known. If so, create a command
		// with it. If not, create a "null" command (for unknown command).
		if (CommandWord.isCommand(word1))
			return new Command(word1, word2);
		else
			return new Command(null, word2);
	}

	public void close() {
		reader.close();
	}

	public Pattern delimiter() {
		return reader.delimiter();
	}

	public boolean equals(Object obj) {
		return reader.equals(obj);
	}

	public String findInLine(Pattern arg0) {
		return reader.findInLine(arg0);
	}

	public String findInLine(String arg0) {
		return reader.findInLine(arg0);
	}

	public String findWithinHorizon(Pattern arg0, int arg1) {
		return reader.findWithinHorizon(arg0, arg1);
	}

	public String findWithinHorizon(String arg0, int arg1) {
		return reader.findWithinHorizon(arg0, arg1);
	}

	public boolean hasNext() {
		return reader.hasNext();
	}

	public boolean hasNext(Pattern arg0) {
		return reader.hasNext(arg0);
	}

	public boolean hasNext(String arg0) {
		return reader.hasNext(arg0);
	}

	public boolean hasNextBigDecimal() {
		return reader.hasNextBigDecimal();
	}

	public boolean hasNextBigInteger() {
		return reader.hasNextBigInteger();
	}

	public boolean hasNextBigInteger(int arg0) {
		return reader.hasNextBigInteger(arg0);
	}

	public boolean hasNextBoolean() {
		return reader.hasNextBoolean();
	}

	public boolean hasNextByte() {
		return reader.hasNextByte();
	}

	public boolean hasNextByte(int arg0) {
		return reader.hasNextByte(arg0);
	}

	public boolean hasNextDouble() {
		return reader.hasNextDouble();
	}

	public boolean hasNextFloat() {
		return reader.hasNextFloat();
	}

	public boolean hasNextInt() {
		return reader.hasNextInt();
	}

	public boolean hasNextInt(int arg0) {
		return reader.hasNextInt(arg0);
	}

	public boolean hasNextLine() {
		return reader.hasNextLine();
	}

	public boolean hasNextLong() {
		return reader.hasNextLong();
	}

	public boolean hasNextLong(int arg0) {
		return reader.hasNextLong(arg0);
	}

	public boolean hasNextShort() {
		return reader.hasNextShort();
	}

	public boolean hasNextShort(int arg0) {
		return reader.hasNextShort(arg0);
	}

	public int hashCode() {
		return reader.hashCode();
	}

	public IOException ioException() {
		return reader.ioException();
	}

	public Locale locale() {
		return reader.locale();
	}

	public MatchResult match() {
		return reader.match();
	}

	public String next() {
		return reader.next();
	}

	public String next(Pattern arg0) {
		return reader.next(arg0);
	}

	public String next(String arg0) {
		return reader.next(arg0);
	}

	public BigDecimal nextBigDecimal() {
		return reader.nextBigDecimal();
	}

	public BigInteger nextBigInteger() {
		return reader.nextBigInteger();
	}

	public BigInteger nextBigInteger(int arg0) {
		return reader.nextBigInteger(arg0);
	}

	public boolean nextBoolean() {
		return reader.nextBoolean();
	}

	public byte nextByte() {
		return reader.nextByte();
	}

	public byte nextByte(int arg0) {
		return reader.nextByte(arg0);
	}

	public double nextDouble() {
		return reader.nextDouble();
	}

	public float nextFloat() {
		return reader.nextFloat();
	}

	public int nextInt() {
		return reader.nextInt();
	}

	public int nextInt(int arg0) {
		return reader.nextInt(arg0);
	}

	public String nextLine() {
		return reader.nextLine();
	}

	public long nextLong() {
		return reader.nextLong();
	}

	public long nextLong(int arg0) {
		return reader.nextLong(arg0);
	}

	public short nextShort() {
		return reader.nextShort();
	}

	public short nextShort(int arg0) {
		return reader.nextShort(arg0);
	}

	public int radix() {
		return reader.radix();
	}

	public void remove() {
		reader.remove();
	}

	public Scanner reset() {
		return reader.reset();
	}

	public Scanner skip(Pattern arg0) {
		return reader.skip(arg0);
	}

	public Scanner skip(String arg0) {
		return reader.skip(arg0);
	}

	public String toString() {
		return reader.toString();
	}

	public Scanner useDelimiter(Pattern arg0) {
		return reader.useDelimiter(arg0);
	}

	public Scanner useDelimiter(String arg0) {
		return reader.useDelimiter(arg0);
	}

	public Scanner useLocale(Locale arg0) {
		return reader.useLocale(arg0);
	}

	public Scanner useRadix(int arg0) {
		return reader.useRadix(arg0);
	}

}
