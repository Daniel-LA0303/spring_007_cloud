package com.la.rabitmq.model;

public class Models {

	public static class Color {

		private String name;

		private String hexadecimal;

		/**
		 * 
		 */
		public Color() {
		}

		/**
		 * @param name
		 * @param hexadecimal
		 */
		public Color(String name, String hexadecimal) {
			this.name = name;
			this.hexadecimal = hexadecimal;
		}

		/**
		 * return the value of the propertie hexadecimal
		 *
		 * @return the hexadecimal
		 */
		public String getHexadecimal() {
			return hexadecimal;
		}

		/**
		 * return the value of the propertie name
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * set the value of the proppertie hexadecimal
		 *
		 * @param hexadecimal the hexadecimal to set
		 */
		public void setHexadecimal(String hexadecimal) {
			this.hexadecimal = hexadecimal;
		}

		/**
		 * set the value of the proppertie name
		 *
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

	}

	public static class SHape {

		private String name;

		private String numberOfSides;

		/**
		 * 
		 */
		public SHape() {
		}

		/**
		 * @param name
		 * @param numberOfSides
		 */
		public SHape(String name, String numberOfSides) {
			this.name = name;
			this.numberOfSides = numberOfSides;
		}

		/**
		 * return the value of the propertie name
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * return the value of the propertie numberOfSides
		 *
		 * @return the numberOfSides
		 */
		public String getNumberOfSides() {
			return numberOfSides;
		}

		/**
		 * set the value of the proppertie name
		 *
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * set the value of the proppertie numberOfSides
		 *
		 * @param numberOfSides the numberOfSides to set
		 */
		public void setNumberOfSides(String numberOfSides) {
			this.numberOfSides = numberOfSides;
		}

	}

}
