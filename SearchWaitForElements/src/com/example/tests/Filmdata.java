package com.example.tests;

public class Filmdata {
	public String filmTitle;
	
	public Filmdata() {
		
	}

	@Override
	public String toString() {
		return "Filmdata [filmTitle=" + filmTitle + "]";
	}

	public Filmdata(String filmTitle) {
		this.filmTitle = filmTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filmTitle == null) ? 0 : filmTitle.hashCode());
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
		Filmdata other = (Filmdata) obj;
		if (filmTitle == null) {
			if (other.filmTitle != null)
				return false;
		} else if (!filmTitle.equals(other.filmTitle))
			return false;
		return true;
	}

	public String getFilmTitle() {
		return filmTitle;
	}

	public void setFilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
	}

	public Filmdata withfilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
		return this;
	}
}