<?php

namespace App;

use Eureka\EurekaClient;
use Symfony\Bundle\FrameworkBundle\Kernel\MicroKernelTrait;
use Symfony\Component\HttpKernel\Kernel as BaseKernel;

class Kernel extends BaseKernel
{
    use MicroKernelTrait;

    public function eurekaClient(): void
    {
        $client = new EurekaClient([
            'eurekaDefaultUrl' => 'http://localhost:8761/eureka',
            'hostName' => 'localhost',
            'appName' => 'EurekaServer',
            'ip' => '127.0.0.1',
            'port' => ['8000', true]
        ]);
        $client->start();
    }

}
