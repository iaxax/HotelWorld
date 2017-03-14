package controller.hotel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import po.pk.BranchPK;
import po.pk.PlanPK;
import service.intf.Hotel;
import vo.hotel.BranchRequestVO;
import vo.hotel.PlanRequestVO;
import vo.result.ResultVO;

public class CEOMsgAction extends ActionSupport {
        
        private boolean success;
        
        private String empId;
        
        private String applyTime;
        
        private String hotelName;
        
        private String roomNum;
        
        private List<BranchRequestVO> branchList;
        
        private List<PlanRequestVO> planList;
        
        private Hotel hotel;
        
        private ResultVO result;
        
        public String checkBranchRequest() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                result = hotel.checkBranchRequest(success, new BranchPK(empId, applyTime));
                return SUCCESS;
        }
        
        public String checkPlanRequest() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                result = hotel.checkPlanRequest(success, new PlanPK(hotelName, roomNum, applyTime));
                return SUCCESS;
        }
        
        public String checkInfoRequest() {
                return SUCCESS;
        }

        public String getBranchRequest() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                branchList = hotel.getBranchRequest();
                return SUCCESS;
        }
        
        public String getPlanRequest() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                planList = hotel.getPlanRequest();
                return SUCCESS;
        }
        
        public String getInfoRequest() {
                return SUCCESS;
        }

        public List<BranchRequestVO> getBranchList() {
                return branchList;
        }

        public void setBranchList(List<BranchRequestVO> branchList) {
                this.branchList = branchList;
        }

        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }

        public List<PlanRequestVO> getPlanList() {
                return planList;
        }

        public void setPlanList(List<PlanRequestVO> planList) {
                this.planList = planList;
        }

        public boolean isSuccess() {
                return success;
        }

        public void setSuccess(boolean success) {
                this.success = success;
        }

        public String getEmpId() {
                return empId;
        }

        public void setEmpId(String empId) {
                this.empId = empId;
        }

        public String getApplyTime() {
                return applyTime;
        }

        public void setApplyTime(String applyTime) {
                this.applyTime = applyTime;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }

        public String getHotelName() {
                return hotelName;
        }

        public void setHotelName(String hotelName) {
                this.hotelName = hotelName;
        }

        public String getRoomNum() {
                return roomNum;
        }

        public void setRoomNum(String roomNum) {
                this.roomNum = roomNum;
        }
        
}
