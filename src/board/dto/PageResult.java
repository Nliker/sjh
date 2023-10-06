package board.dto;

public class PageResult {
	private int count;
	private int pageNo;
	private int beginPage, endPage;
	private boolean prev, next;
	private int listSize, tabSize;
	
	public PageResult(int pageNo, int count) {
		this(pageNo, count, 10, 10);
	}
	public PageResult(int pageNo, int count, int listSize, int tabSize) {
		this.count = count;
		this.pageNo = pageNo;
		this.listSize = listSize;
		this.tabSize = tabSize;
		cal();
	}
	private void cal() {
		int lastPage = (int)Math.ceil((double)count / listSize);
		int currTab = (pageNo - 1) / tabSize + 1;
		beginPage = (currTab - 1) * tabSize + 1;
		endPage = (currTab * tabSize < lastPage) ? currTab * tabSize
				                                     : lastPage;
		prev = beginPage != 1;
		next = endPage != lastPage;
	}
	public int getCount() {
		return count;
	}
	public int getPageNo() {
		return pageNo;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean getPrev() {
		return prev;
	}
	public boolean getNext() {
		return next;
	}
}