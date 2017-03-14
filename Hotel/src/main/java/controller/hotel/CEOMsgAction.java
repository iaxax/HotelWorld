package controller.hotel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import po.pk.BranchPK;
import service.intf.Hotel;
import vo.hotel.BranchRequestVO;
import vo.hotel.PlanRequestVO;
import vo.result.ResultVO;

public class CEOMsgAction extends ActionSupport {
        
        private boolean isSuccess;
        
        private String empId;
        
        private String applyTime;
        
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
                
                result = hotel.checkBranchRequest(isSuccess, new BranchPK(empId, applyTime));
                return SUCCESS;
        }
        
        public String checkPlanRequest() {
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
                return isSuccess;
        }

        public void setSuccess(boolean isSuccess) {
                this.isSuccess = isSuccess;
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
        
}
