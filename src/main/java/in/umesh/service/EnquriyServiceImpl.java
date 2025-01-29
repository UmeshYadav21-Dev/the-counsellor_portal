package in.umesh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.umesh.dto.ViewEnqsFilterRequest;
import in.umesh.entities.Counsellor;
import in.umesh.entities.Enquriy;
import in.umesh.repository.CounsellorRepository;
import in.umesh.repository.EnquiryRepository;
import io.micrometer.common.util.StringUtils;

@Service
public class EnquriyServiceImpl implements EnquriyService {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private CounsellorRepository counsellorRepository;

    @Override
    public boolean addEnquiry(Enquriy enq, Integer counsellorId) throws Exception {
        Counsellor counsellor = counsellorRepository.findById(counsellorId).orElse(null);
        if (counsellor == null) {
            throw new Exception("No Counsellor Found");
        }

        enq.setCounsellor(counsellor);

        Enquriy saved = enquiryRepository.save(enq);
        
        if(saved.getEnqId() != null) {
        	return true;
        }

        return false;
    }

    @Override
    public List<Enquriy> getAllEnquiries(Integer counsellorId) {
        return enquiryRepository.getEnquiriesByCounsellorId(counsellorId);
    }

    @Override
    public List<Enquriy> getEnquiresWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId) {
        Enquriy enq = new Enquriy();

        if (StringUtils.isNotEmpty(filterReq.getClassMode())) {
            enq.setClassMode(filterReq.getClassMode());
        }

        if (StringUtils.isNotEmpty(filterReq.getCourseName())) {
            enq.setCourseName(filterReq.getCourseName());
        }

        if (StringUtils.isNotEmpty(filterReq.getEnqStatus())) {
            enq.setEnqStatus(filterReq.getEnqStatus());
        }

        Counsellor c = counsellorRepository.findById(counsellorId).orElse(null);
        enq.setCounsellor(c);

        Example<Enquriy> of = Example.of(enq);

        List<Enquriy> enqList = enquiryRepository.findAll(of);

        return enqList;
    }

    @Override
    public Enquriy getEnquiryById(Integer enqId) {
        return enquiryRepository.findById(enqId).orElse(null);
    }

   
}
