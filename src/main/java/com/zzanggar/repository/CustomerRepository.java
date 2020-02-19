package com.zzanggar.repository;

import com.zzanggar.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    boolean existsByLicenseNumberAndManagerNameAndPositionAndDepartment(String licenseNumber, String managerName, String position, String department);

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdxNot(String email, Long idx);

    Optional<Customer> findFirstByLicenseNumber(String licenseNumber);

    Optional<Customer> findFirstByEmail(String email);

    Page<Customer> findAllByOrderByCreatedTime(Pageable pageable);

    Page<Customer> findAllByCreatedTimeGreaterThanEqualOrderByCreatedTime(Date startDate, Pageable pageable);

    Page<Customer> findAllByCreatedTimeLessThanEqualOrderByCreatedTime(Date endDate, Pageable pageable);

    Page<Customer> findAllByCreatedTimeIsBetweenOrderByCreatedTime(Date startDate, Date endDate, Pageable pageable);

    Page<Customer> findAllByEmailContaining(String keyword, Pageable pageable);

    Page<Customer> findAllByCompanyNameContaining(String keyword, Pageable pageable);

    Page<Customer> findAllByManagerNameContaining(String keyword, Pageable pageable);

    Page<Customer> findAllByFinalCreatorContaining(String keyword, Pageable pageable);

    @Query("SELECT c.businessCategory FROM Customer c GROUP BY c.businessCategory ORDER BY c.businessCategory")
    List<String> findAllBusinessCategoryQuery();

    @Query("SELECT c.position FROM Customer c GROUP BY c.position ORDER BY c.position")
    List<String> findAllPositionQuery();

    @Query("SELECT c.department FROM Customer c GROUP BY c.department ORDER BY c.department")
    List<String> findAllDepartmentQuery();

    @Query("SELECT c.finalCreator FROM Customer c GROUP BY c.finalCreator ORDER BY c.finalCreator")
    List<String> findAllFinalCreatorQuery();

    @Query(value = "select to_char(CREATED_TIME, 'YYYY') as year, \n" +
            "       to_char(CREATED_TIME, 'MM') as month, \n" +
            "       count(*) as count\n" +
            "from CUSTOMER\n" +
            "group by to_char(CREATED_TIME, 'YYYY'), to_char(CREATED_TIME, 'MM')\n" +
            "order by 2", nativeQuery = true)
    List<Map> counterPerMonth();

    @Query(value = "select to_char(CREATED_TIME, 'YYYY') as year, \n" +
            "       BUSINESS_CATEGORY as category, \n" +
            "       count(*) as count\n" +
            "from CUSTOMER\n" +
            "group by year, BUSINESS_CATEGORY\n" +
            "ORDER BY 2", nativeQuery = true)
    List<Map> counterBusinessCategory();

    @Transactional
    @Modifying
    @Query("delete from Customer c where c.idx in :idxes")
    void deleteAllByIdxInQuery(@Param("idxes") List<Long> idxes);
}
