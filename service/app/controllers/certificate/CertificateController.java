package controllers.certificate;

import controllers.BaseController;
import org.sunbird.common.models.util.ActorOperations;
import org.sunbird.common.request.Request;
import org.sunbird.learner.actor.operations.CourseActorOperations;
import java.util.concurrent.CompletionStage;

import play.mvc.Http;
import play.mvc.Result;

public class CertificateController extends BaseController {

  public static final String REISSUE = "reIssue";

  public CompletionStage<Result> issueCertificate(Http.Request httpRequest) {
    return handleRequest(
        CourseActorOperations.ISSUE_CERTIFICATE.getValue(),
        httpRequest.body().asJson(),
        (request) -> {
          Request req = (Request) request;
          new CertificateRequestValidator().validateIssueCertificateRequest(req);
          req.getContext().put(REISSUE, httpRequest.queryString().get(REISSUE));
          return null;
        },
        getAllRequestHeaders(httpRequest),
            httpRequest);
  }

    public CompletionStage<Result> addCertificate(Http.Request httpRequest) {
        return handleRequest(
                CourseActorOperations.ADD_CERTIFICATE.getValue(),
                httpRequest.body().asJson(),
                (request) -> {
                    Request req = (Request) request;
                    new CertificateRequestValidator().validateAddCertificateRequest(req);
                    return null;
                },
                getAllRequestHeaders(httpRequest),
                httpRequest);
    }

    public CompletionStage<Result> getCertificateList(Http.Request httpRequest) {
        return handleRequest(
                CourseActorOperations.GET_CERTIFICATE.getValue(),
                httpRequest.body().asJson(),
                (request) -> {
                    Request req = (Request) request;
                    new CertificateRequestValidator().validateGetCertificateListRequest(req);
                    return null;
                },
                getAllRequestHeaders(httpRequest),
                httpRequest);
    }
}
