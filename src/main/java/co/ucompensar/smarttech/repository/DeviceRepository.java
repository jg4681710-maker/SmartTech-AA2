package co.ucompensar.smarttech.repository;

import co.ucompensar.smarttech.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("select d from Device d join fetch d.brand b " +
           "where (:keyword is null or " +
           "lower(d.name) like lower(concat('%', cast(:keyword as String), '%'))) " +
           "and (:brandId is null or b.id = :brandId) " +
           "and (:type is null or d.type = :type) " +
           "order by d.releaseDate desc")
    List<Device> search(@Param("keyword") String keyword,
                        @Param("brandId") Long brandId,
                        @Param("type") String type);
}
