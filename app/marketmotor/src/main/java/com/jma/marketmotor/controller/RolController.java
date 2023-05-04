package com.jma.marketmotor.controller;

import com.jma.marketmotor.api.permiso.PermisoCommandInsert;
import com.jma.marketmotor.api.rol.RolCommandInsert;
import com.jma.marketmotor.api.rol.RolCommandUpdate;
import com.jma.marketmotor.dto.PermisoDto;
import com.jma.marketmotor.dto.RolDto;
import com.jma.marketmotor.mapping.RolMapper;
import com.jma.marketmotor.repository.PermisoRepository;
import com.jma.marketmotor.service.PermisoService;
import com.jma.marketmotor.service.RolService;
import com.jma.marketmotor.utils.EstadoD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/roles")
public class RolController {

    RolService<RolDto> rolService;
    private final PermisoService<PermisoDto> permisoService;

    @Autowired
    public RolController(RolService<RolDto> rolService,
                         PermisoService<PermisoDto> permisoService){
        this.rolService = rolService;
        this.permisoService = permisoService;
    }


    @PostMapping
    public ResponseEntity<RolDto> guardar(@RequestBody RolCommandInsert rolCommandInsert){

        RolDto rolDtoObt = rolService.guardar(RolMapper.mapFromCommandInsertToDto(rolCommandInsert));
        return ResponseEntity.ok(rolDtoObt);

    }

    @GetMapping
    public ResponseEntity<RolDto> obtenerPorId(@PathVariable Long id){

        RolDto rolDtoObt = rolService.obtenerPorId(id);

        return ResponseEntity.ok(rolDtoObt);
    }

    @PutMapping
    public ResponseEntity<RolDto> actualizar(@RequestBody RolCommandUpdate rolCommandUpdate) {

        RolDto rolDtoObt = rolService.actualizar(RolMapper.mapFromCommandUpdateToDto(rolCommandUpdate));

        return ResponseEntity.ok(rolDtoObt);

    }

    @PatchMapping(name = "/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Long id){

        try{
            RolDto rol = rolService.obtenerPorId(id);
            if(rol == null)
                return ResponseEntity.notFound().build();

            rol.declararDisponibilidad(EstadoD.INACTIVO);
            rolService.guardar(rol);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        String respuesta = rolService.eliminar(id);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/{id_rol}/permisos")
    public ResponseEntity<PermisoDto> definirPermiso(@PathVariable(value = "id_rol") Long idRol, @RequestBody PermisoDto permisoRequest) {
        PermisoDto permisoRolConfigured = rolService.definirPermiso(idRol,permisoRequest);
        return ResponseEntity.ok(permisoRolConfigured);
    }

    @DeleteMapping("/{id_rol}/permisos/{id_permiso}")
    public ResponseEntity<RolDto> removerPermiso(@PathVariable(value = "id_rol") Long idRol, @PathVariable(value = "id_permiso") Long idPermiso) {
        RolDto rol = rolService.removerPermiso(idRol,idPermiso);
        return ResponseEntity.ok(rol);
    }


    @GetMapping("/permisos/{permisoId}/roles")
    public ResponseEntity<List<RolDto>> obtenerTodosRolesPorPermisoId(@PathVariable(value = "permisoId") Long permisoId) {
        if (permisoService.obtenerPorId(permisoId)!=null) {
            throw new RuntimeException("No fue encontrado el permiso" + permisoId);
        }

        List<RolDto> rolesObt = rolService.buscarRolesPorPermisosId(permisoId);
        return ResponseEntity.ok(rolesObt);
    }


    @GetMapping("/{rolId}/permisos")
    public ResponseEntity<List<PermisoDto>> obtenerTodosPermisosPorRolId(@PathVariable(value = "rolId") Long rolId) {
        if (rolService.obtenerPorId(rolId)!=null) {
            throw new RuntimeException("No fue encontrado el rol = " + rolId);
        }

        List<PermisoDto> permisosObt = permisoService.buscarPermisosPorRolId(rolId);
        return ResponseEntity.ok(permisosObt);
    }




}
