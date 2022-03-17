package com.ias.training.Maintenance.infrastruture.gateways;

import com.ias.training.Maintenance.core.domain.MaintenanceService;
import com.ias.training.Maintenance.core.domain.MaintenanceServiceId;
import com.ias.training.Maintenance.core.gateways.MaintenanceRepository;
import com.ias.training.Maintenance.infrastruture.gateways.models.MaintenanceDBO;
import com.ias.training.Maintenance.shared.domain.PageQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SqlMaintenancesRepository implements MaintenanceRepository {
    private final DataSource dataSource;

    public SqlMaintenancesRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<MaintenanceService> query(PageQuery pageQuery) {
        String sql = "SELECT * FROM maintenance LIMIT ? OFFSET ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, pageQuery.getLimit().getValue());
            preparedStatement.setInt(2, pageQuery.getSkip().getValue());


            ResultSet resultSet = preparedStatement.executeQuery();
            List<MaintenanceService> result = new ArrayList<>();

            while (resultSet.next()) {
                MaintenanceDBO dbo = new MaintenanceDBO();
                dbo.setId(resultSet.getString("service_id"));
                dbo.setStartService(resultSet.getTimestamp("start").toLocalDateTime());
                dbo.setEndService(resultSet.getTimestamp("finish").toLocalDateTime());
                dbo.setDescripcion(resultSet.getString("description"));
                MaintenanceService domainProduct = dbo.toDomain();
                result.add(domainProduct);
            }

            resultSet.close();

            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public Optional<MaintenanceService> get(MaintenanceServiceId maintenanceServiceId) {
        String sql = "SELECT * FROM maintenance WHERE service_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, maintenanceServiceId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                MaintenanceDBO dbo = MaintenanceDBO.fromResultSet(resultSet);
                MaintenanceService domainService = dbo.toDomain();
                return Optional.of(domainService);
            } else {
                return Optional.empty();
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public void create(MaintenanceService maintenanceService) {
        String sql = "INSERT INTO maintenance (service_id, start, finish, description) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, maintenanceService.getId().toString());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(maintenanceService.getStartService()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(maintenanceService.getEndService()));
            preparedStatement.setString(4, maintenanceService.getDescripcion().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }

    }

    @Override
    public void update(MaintenanceService maintenanceService) {
        String sql = "UPDATE maintenance SET start = ?, finish = ?, description = ? WHERE service_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setTimestamp(1, Timestamp.valueOf(maintenanceService.getStartService()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(maintenanceService.getEndService()));
            preparedStatement.setString(3, maintenanceService.getDescripcion().toString());
            preparedStatement.setString(4, maintenanceService.getId().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }

    }

    @Override
    public void delete(MaintenanceServiceId maintenanceServiceId) {
        String sql = "DELETE FROM maintenance WHERE service_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, maintenanceServiceId.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }

    }
}
