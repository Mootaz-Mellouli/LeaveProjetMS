<?php

namespace App\Controller;

use App\Entity\User;
use App\Repository\ClaimRepository;
use Eureka\EurekaClient;
use Eureka\Exceptions\RegisterFailureException;
use http\Client\Request;
use http\Client\Response;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Contracts\HttpClient\HttpClientInterface;

class ClaimController extends AbstractController
{
    private $claimRepo;

    public function __construct(
        private HttpClientInterface $client,
        ClaimRepository $claimRepository
    ) {
        $this->claimRepo = $claimRepository;
    }

    /**
     * @throws RegisterFailureException
     */
    #[Route('/claim', name: 'book', methods: ['GET'])]
    public function index(): JsonResponse
    {
        $client = new EurekaClient([
            'eurekaDefaultUrl' => 'http://mootaz:mootaz@localhost:8761/eureka/',
            'hostName' => 'localhost',
            'appName' => 'ClaimMS',
            'ip' => '127.0.0.1',
            'port' => ['8000', true]
        ]);
        $client->getConfig()->setHeartbeatInterval(5);
        //$client->start();
        $client->register();
        $client->heartbeat();
        return $this->json([
            'message' => 'Welcome to your new controller!',
            'path' => 'src/Controller/ClaimController.php',
        ]);
    }

    #[Route('/claim/token', name: 'token', methods: ['GET'])]
    public function getJwtToken(): array
    {

        /*$this->client = $client->withOptions(
            (new HttpOptions())
                ->setBaseUri('https://...')
                ->setHeaders(['header-name' => 'header-value'])
                ->toArray()
        );*/
        $response = $this->client->request(
            'GET',
            ''
        );

        $statusCode = $response->getStatusCode();
        $user = $response->getContent();
        // $content = '{"id":521583, "name":"symfony-docs", ...}'
        $content = $response->toArray();
        // $content = ['id' => 521583, 'name' => 'symfony-docs', ...]

        return $content;
    }
    #[Route('/claim/add', name: 'addClaim', methods: ['POST'])]
    public function add(Request $request): JsonResponse
    {
        $user = new User();
        // add token to request
        /*$this->client = $client->withOptions(
            (new HttpOptions())
                ->setBaseUri('https://...')
                ->setHeaders(['header-name' => 'header-value'])
                ->toArray()
        );*/
        // get user
        $response = $this->client->request(
            'GET',
            ''
        );

        $statusCode = $response->getStatusCode();
        // get User data
        //$user = $response->getContent();

    $data = json_decode($request->getContent(), true);

    $description = $data['description'];
    $claimDate = $data['claimDate'];
    $feedBack = $data['feedBack'];
    //$matriculeClient = $user->getMatricule();

    /*if (empty($description) || empty($claimDate) || empty($feedBack) ) {
        throw new NotFoundHttpException('Expecting mandatory parameters!');
    }*/

    $this->claimRepo->saveClaim($description, $claimDate, $feedBack, 'test');

    return new JsonResponse(['status' => 'Customer created!']);
    }


}
