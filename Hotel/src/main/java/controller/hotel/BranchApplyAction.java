package controller.hotel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Hotel;
import vo.hotel.BranchVO;
import vo.result.ResultVO;

public class BranchApplyAction extends ActionSupport {
        
        private static final long serialVersionUID = 5181331864484343016L;

        private String hotelName;
        
        private String hotelAddr;
        
        private String openDate;
        
        private ResultVO result;
        
        private Hotel hotel;

        public String branchApply() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                result = hotel.branchApply(new BranchVO(
                                (String)session.getAttribute("id"),
                                hotelName, hotelAddr, openDate
                ));
                return SUCCESS;
        }

        public String getHotelName() {
                return hotelName;
        }

        public void setHotelName(String hotelName) {
                this.hotelName = hotelName;
        }

        public String getHotelAddr() {
                return hotelAddr;
        }

        public void setHotelAddr(String hotelAddr) {
                this.hotelAddr = hotelAddr;
        }

        public String getOpenDate() {
                return openDate;
        }

        public void setOpenDate(String openDate) {
                this.openDate = openDate;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }

        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }
        
}
