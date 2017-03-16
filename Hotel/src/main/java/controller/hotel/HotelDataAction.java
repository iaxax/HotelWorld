package controller.hotel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Hotel;
import vo.hotel.HotelInfoVO;
import vo.hotel.HotelModifyVO;
import vo.result.ResultVO;

public class HotelDataAction extends ActionSupport {
        
        private static final long serialVersionUID = 2154273371012254929L;

        private HotelInfoVO hotelInfo;
        
        private Hotel hotel;
        
        private String name;
        
        private String address;
        
        private ResultVO result;
        
        private List<Map<String, Integer>> statInfo;

        public String getHotelBasicInfo() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                hotelInfo = hotel.getHotelBasicInfo((String)session.getAttribute("id"));
                return SUCCESS;
        }
        
        public String getHotelStat() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                statInfo = hotel.getHotelStat((String)session.getAttribute("id"));
                return SUCCESS;
        }
        
        public String modifyHotelInfo() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                result = hotel.modifyHotelInfo(new HotelModifyVO(
                                (String)session.getAttribute("id"),
                                name, address
                ));
                return SUCCESS;
        }

        public HotelInfoVO getHotelInfo() {
                return hotelInfo;
        }

        public void setHotelInfo(HotelInfoVO hotelInfo) {
                this.hotelInfo = hotelInfo;
        }

        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }

        public List<Map<String, Integer>> getStatInfo() {
                return statInfo;
        }

        public void setStatInfo(List<Map<String, Integer>> statInfo) {
                this.statInfo = statInfo;
        }
        
}
