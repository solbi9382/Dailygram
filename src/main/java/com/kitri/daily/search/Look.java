package com.kitri.daily.search;

public class Look {
	private String id;
	private int row;
	private int board_seq;
	private String img;
	public Look() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Look(String id, int row, int board_seq, String img) {
		super();
		this.id = id;
		this.row = row;
		this.board_seq = board_seq;
		this.img = img;
	}

	@Override
	public String toString() {
		return "Look [id=" + id + ", row=" + row + ", board_seq=" + board_seq + ", img=" + img + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Look(String id, int row) {
		super();
		this.id = id;
		this.row = row;
	}
		
}
