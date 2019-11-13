package damain;

import java.util.List;

public class pageUsers {
    private int curpage;//当前页码
    private int pagecount;//每页显示的数量
    private List<User> pageUsers;//返回的用户列表
    private int totalCount;//总共的用户数量
    private int totalPages;//总共的页码数量


    @Override
    public String toString() {
        return "pageUsers{" +
                "curpage=" + curpage +
                ", pagecount=" + pagecount +
                ", pageUsers=" + pageUsers +
                ", totalCount=" + totalCount +
                ", totalPages=" + totalPages +
                '}';
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public List<User> getPageUsers() {
        return pageUsers;
    }

    public void setPageUsers(List<User> pageUsers) {
        this.pageUsers = pageUsers;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
