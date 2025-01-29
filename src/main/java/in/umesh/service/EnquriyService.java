package in.umesh.service;

import java.util.List;

import in.umesh.dto.ViewEnqsFilterRequest;
import in.umesh.entities.Enquriy;

public interface EnquriyService {

    boolean addEnquiry(Enquriy enq, Integer counsellorId) throws Exception;

    List<Enquriy> getAllEnquiries(Integer counsellorId);

    List<Enquriy> getEnquiresWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId);

   public  Enquriy getEnquiryById(Integer enqId);

    
}
