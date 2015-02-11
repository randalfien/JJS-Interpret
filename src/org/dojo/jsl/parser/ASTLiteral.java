package org.dojo.jsl.parser;

import org.dojo.jsl.parser.EcmaScript;
import org.dojo.jsl.parser.EcmaScriptVisitor;
import org.dojo.jsl.parser.ParseException;
import org.dojo.jsl.parser.Token;

import cz.fit.cvut.paskaond.jsruntime.Evalable;
import cz.fit.cvut.paskaond.jsruntime.JSEnvironment;
import cz.fit.cvut.paskaond.jsruntime.builtin.primitive.JSNull;

public class ASTLiteral extends SimpleNode implements Evalable {

	static public final Object REGEX = new Object();

	static public final Object HTML = new Object();

	protected String tokenImage;

	protected Object value;
	
	protected String valueType;
	
	public ASTLiteral(int id) {
		super(id);
	}

	public ASTLiteral(EcmaScript p, int id) {
		super(p, id);
	}

	/** Accept the visitor. * */
	@Override
	public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	public String getTokenImage() {
		return tokenImage;
	}

	static public String unescapedString(String image) {
		image = image.substring(1, image.length() - 1);

		int l = image.length();
		StringBuffer sb = new StringBuffer(l);

		/*
		 * 
		 * \XXX - The character specified by up to three octal digits XXX
		 * between 0 and 377 ( example: \251 ) \xXX - The character specified by
		 * the two hexadecimal digits XX between 00 and FF ( example: \xA9 ) \
		 * uXXXX - The Unicode character specified by the four hexadecimal
		 * digits XXXX ( example: \u00A9 )
		 */
		for (int i = 0; i < l; i++) {
			char c = image.charAt(i);
			if ((c == '\\') && (i + 1 < l)) {
				i++;
				c = image.charAt(i);
				if (c == 'n') {
					c = '\n';
				} else if (c == 'b') {
					c = '\b';
				} else if (c == 'f') {
					c = '\f';
				} else if (c == 'r') {
					c = '\r';
				} else if (c == 't') {
					c = '\t';
				} else if (c == 'x' && l == 4) {
					c = (char) (hexval(image.charAt(i + 1)) << 4 | hexval(image
							.charAt(i + 1)));
					i += 2;
				} else if (c == 'u') {
					c = (char) (hexval(image.charAt(i + 1)) << 12
							| hexval(image.charAt(i + 2)) << 8
							| hexval(image.charAt(i + 3)) << 4 | hexval(image
							.charAt(i + 4)));
					i += 4;
				} else if ((c >= '0') && (c <= '7') && l == 4) {
					c = (char) (octval(image.charAt(i)));
					if ((image.length() > i) && (image.charAt(i + 1) >= '0')
							&& (image.charAt(i + 1) <= '7')) {
						i++;
						c = (char) ((c << 4) | octval(image.charAt(i)));
					}
				}
			}
			sb.append(c);
		}
		return sb.toString();
	}

	static public String escapedString(String image, char quoteChar) {
		int l = image.length();
		StringBuffer sb = new StringBuffer(l);

		for (int i = 0; i < l; i++) {
			char c = image.charAt(i);
			if (c == '\n') {
				sb.append("\\n");
			} else if (c == '\r') {
				sb.append("\\r");
			} else if (c == '\b') {
				sb.append("\\b");
			} else if (c == '\f') {
				sb.append("\\f");
			} else if (c == '\t') {
				sb.append("\\t");
			} else if (c == quoteChar) {
				sb.append("\\");
				sb.append(quoteChar);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public void setStringValue(String image) throws ParseException {
		tokenImage = image;
		value = ASTLiteral.unescapedString(image);
		valueType = "String";
	}

	public void setUnquotedStringValue(String valueStr) {
		value = valueStr;

		Token token = getBeginToken();

		char quoteChar = token.image.charAt(0);
		if (quoteChar == '"') {
			token.image = "\"" + ASTLiteral.escapedString(valueStr, quoteChar)
					+ "\"";
		} else {
			token.image = "'" + ASTLiteral.escapedString(valueStr, quoteChar)
					+ "'";
		}
	}

	public void setRegexValue(String image) {
		tokenImage = image;
		value = ASTLiteral.REGEX;
		valueType = "Regex";
	}

	public void setHtmlValue(String image) {
		tokenImage = image;
		value = ASTLiteral.HTML;
		valueType = "HTML";
	}

	public void setDecimalValue(String image) {
		tokenImage = image;

		try {
			value = new Long(image);
		} catch (NumberFormatException e) {
			// it's a floating point
			value = new Double(image);
		}
		valueType = "Number";
	}

	public void setHexValue(String image) {
		tokenImage = image;
		value = new Long(Long.parseLong(image.substring(2), 16));
		valueType = "Number";
	}

	public void setFloatingPointValue(String image) {
		tokenImage = image;
		value = new Double(image);
		valueType = "Number";
	}

	public void setBooleanValue(String image) {
		tokenImage = image;
		value = new Boolean(image);
		valueType = "Boolean";
	}

	public void setNullValue() {
		value = JSNull.getInstance();
		valueType = "Null";
	}

	@Override
	public String toString() {
		return valueType+"Literal[" + value.toString() + "]";
	}

	/**
	 * Overwrites <code>equals</code> from <code>Object</code>.
	 * 
	 * @param obj
	 *            a object
	 * @return true if specified object equal to receiver
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj.getClass().equals(this.getClass()))) {
			ASTLiteral other = (ASTLiteral) obj;
			boolean result = true;

			if (tokenImage != null) {
				result = tokenImage.equals(other.tokenImage);
			} else {
				result = (other.tokenImage == null);
			}

			if (result) {
				result = value.equals(other.value);
			}

			return result;
		}

		return false;
	}

	/**
	 * Overwrites <code>hashCode</code> from <code>Object</code>.
	 * 
	 * @return hash code of receiver
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 17;

		if (tokenImage != null) {
			result = 37 * result + tokenImage.hashCode();
		} else {
			result = 37 * result;
		}

		if (value != null) {
			result = 37 * result + value.hashCode();
		} else {
			result = 37 * result;
		}

		return result;
	}

	public static final int hexval(char c) {
		return  Character.getNumericValue(c) ;
	}

	static final int octval(char c) {
		return Character.getNumericValue(c) ;
	}

	@Override
	public Object evalIn(JSEnvironment env) {
		return value;
	}

}