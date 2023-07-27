package cn.koala.ocr;

import ai.djl.modality.cv.output.DetectedObjects;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * OCR管理接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/ocr")
@Tag(name = "OCR管理")
@SecurityRequirement(name = "spring-security")
public interface OcrApi {

  @PreAuthorize("hasAuthority('ocr:image')")
  @Operation(operationId = "ocrImage", summary = "识别图片")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ImageResult.class))}
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
    content = @Content(
      mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
      schema = @Schema(type = "object"),
      schemaProperties = @SchemaProperty(name = "file", schema = @Schema(type = "string", format = "binary"))
    ),
    required = true
  )
  @PostMapping("image")
  DataResponse<List<DetectedObjects.DetectedObject>> image(@RequestParam("file") MultipartFile image);

  @PreAuthorize("hasAuthority('ocr:pdf')")
  @Operation(operationId = "ocrPDF", summary = "识别PDF")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PDFResult.class))}
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
    content = @Content(
      mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
      schema = @Schema(type = "object"),
      schemaProperties = @SchemaProperty(name = "file", schema = @Schema(type = "string", format = "binary"))
    ),
    required = true
  )
  @PostMapping("pdf")
  DataResponse<List<String>> pdf(@RequestParam("file") MultipartFile pdf);

  class ImageResult extends DataResponse<List<DetectedObjects.DetectedObject>> {

  }

  class PDFResult extends DataResponse<List<String>> {

  }
}
