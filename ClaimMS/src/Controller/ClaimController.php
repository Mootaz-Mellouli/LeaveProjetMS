<?php

namespace App\Controller;

use Eureka\EurekaClient;
use Eureka\Exceptions\RegisterFailureException;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Contracts\HttpClient\HttpClientInterface;

class ClaimController extends AbstractController
{
    public function __construct(
        private HttpClientInterface $client,
    ) {
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

    public function getClaimsNotArchivedByUser(): array
    {
        /*$this->client = $client->withOptions(
            (new HttpOptions())
                ->setBaseUri('https://...')
                ->setHeaders(['header-name' => 'header-value'])
                ->toArray()
        );*/
        $response = $this->client->request(
            'GET',
            'https://api.github.com/repos/symfony/symfony-docs'
        );

        $statusCode = $response->getStatusCode();
        // $statusCode = 200
        $contentType = $response->getHeaders()['content-type'][0];
        // $contentType = 'application/json'
        $content = $response->getContent();
        // $content = '{"id":521583, "name":"symfony-docs", ...}'
        $content = $response->toArray();
        // $content = ['id' => 521583, 'name' => 'symfony-docs', ...]

        return $content;
    }


}
