package controller.hotel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Hotel;
import vo.hotel.PlanVO;
import vo.result.ResultVO;

public class PlanAction extends ActionSupport {
        
        private static final long serialVersionUID = 378012438382564926L;

        private ResultVO result;
        
        private Hotel hotel;
        
        private String roomNum;
        
        private int roomPrice;

        public String publishPlan() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                result = hotel.publishPlan(new PlanVO(
                                (String)session.getAttribute("id"),
                                roomNum, roomPrice
                ));
                return SUCCESS;
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

        public String getRoomNum() {
                return roomNum;
        }

        public void setRoomNum(String roomNum) {
                this.roomNum = roomNum;
        }

        public int getRoomPrice() {
                return roomPrice;
        }

        public void setRoomPrice(int roomPrice) {
                this.roomPrice = roomPrice;
        }
        
}
