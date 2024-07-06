package output;

import com.example.webbanmypham.dto.CartDTO;

import java.util.ArrayList;
import java.util.List;

public class CartOutput {
    private int page;
    private int totalPage;
    private List<CartDTO> listResult = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<CartDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<CartDTO> listResult) {
        this.listResult = listResult;
    }
}
