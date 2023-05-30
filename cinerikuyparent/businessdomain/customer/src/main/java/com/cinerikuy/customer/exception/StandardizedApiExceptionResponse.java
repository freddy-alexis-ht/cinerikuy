package com.cinerikuy.customer.exception;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
    Standardizing rest API error reports
*/

@Getter @Setter
public class StandardizedApiExceptionResponse {

    @ApiModelProperty(notes = "The unique uri identifier that categorizes the error", name = "type",
            required = true, example = "/errors/authentication/not-authorized")
    private String type ="/errors/validation";
    @ApiModelProperty(notes = "A brief, human-readable message about the error", name = "title",
            required = true, example = "The user does not have authorization")
    private String title;
    @ApiModelProperty(notes = "The unique error code", name = "code",
            required = false, example = "192")
    private String code;
    @ApiModelProperty(notes = "A human-readable explanation of the error", name = "detail",
            required = true, example = "The user does not have the proper permissions to access the "
            + "resource, please contact with us https://mybusiness.com#contactus")
    private String detail;
    @ApiModelProperty(notes = "A URI that identifies the specific occurrence of the error", name = "detail",
            required = true, example = "/errors/authentication/not-authorized/01")
    private String instance ="/errors/validation/01";

    public StandardizedApiExceptionResponse(String title, String code, String detail) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
    }
}
